package com.zomato.auth.service;

import com.zomato.auth.entity.Address;
import com.zomato.auth.entity.Role;
import com.zomato.auth.entity.User;
import com.zomato.auth.enums.AccountStatus;
import com.zomato.auth.repository.UserRepository;
import com.zomato.auth.requestDTO.AddressDTO;
import com.zomato.auth.requestDTO.UserLoginRequestDTO;
import com.zomato.auth.requestDTO.UserRegisterRequestDTO;
import com.zomato.auth.responseDTO.UserLoginResponseDTO;
import com.zomato.auth.security.jwt.JwtService;
import com.zomato.auth.security.jwt.JwtUtil;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public void registerUser(UserRegisterRequestDTO dto) {
        Optional<User> user =  userRepository.findByUsername(dto.username());

        if(user.isPresent()){
            throw new  IllegalArgumentException("USERNAME_ALREADY_EXIST");
        }

        if(StringUtils.isNotBlank(dto.emailId())) {
            user = userRepository.findByEmailId(dto.emailId());
            if(user.isPresent()){
                throw new  IllegalArgumentException("EMAIL_ALREADY_EXIST");
            }
        }
        if(StringUtils.isNotBlank(dto.phoneNumber())){
            user = userRepository.findByPhoneNumber(dto.phoneNumber());
            if(user.isPresent()) {
                throw new IllegalArgumentException("PHONENUMBER_ALREADY_EXIST");
            }
        }

        Role role = Role.builder().name(dto.role()).role("ROLE_"+dto.role()).description(dto.role()).build();

        List<Address> addresses = new ArrayList<>();

        for(AddressDTO addressDTO: dto.addresses()){
            Address address  = Address.builder().city(addressDTO.city()).state(addressDTO.state())
                    .country(addressDTO.country()).deliveryAddress(addressDTO.deliveryAddress())
                    .pincode(addressDTO.pincode()).type(addressDTO.type()).build();
            addresses.add(address);
        }


        User userRequest =  User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .emailId(dto.emailId())
                .phoneNumber(dto.phoneNumber())
                .role(role)
                .addresses(addresses)
                .status(AccountStatus.PENDING_VERIFICATION)
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .createdBy("SYSTEM")
                .build();

        User savedUser = userRepository.save(userRequest);
        log.info("USER_CREATED_SUCCESSFULLY id {}",savedUser.getId());
    }

    public UserLoginResponseDTO loginUser(UserLoginRequestDTO dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());
        Optional<User> user = userRepository.findByUsernameAndPassword(dto.username(),encodedPassword);

        if(user.isEmpty()){
            user = userRepository.findByEmailIdAndPassword(dto.username(),encodedPassword);
        }

        if(user.isEmpty()){
            user = userRepository.findByPhoneNumberAndPassword(dto.username(),encodedPassword);
        }

        if(user.isEmpty()){
            throw new UsernameNotFoundException("INVALID_USERNAME_PASSWORD");
        }


        String accessToken = jwtService.generateAccessToken(user.get());
        String refreshToken = jwtService.generateRefreshToken(user.get());

        return new UserLoginResponseDTO(accessToken,refreshToken);


    }
}
