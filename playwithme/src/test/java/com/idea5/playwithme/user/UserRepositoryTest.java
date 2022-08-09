package com.idea5.playwithme.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootApplication
class UserRepositoryTest {

    @Autowired
    UsersRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void 회원_등록() {
        // given
        String email = "tmdrms0705@gmail.com";
        String ageRange = "20~29: 20세 이상 30세 미만";
        String name = "최승근";
        Integer mannerTemp = 65;
        String gender = "male";

        Long savedId = userRepository.save(Users.builder()
                .email(email)
                .ageRange(ageRange)
                .name(name)
                .mannerTemp(mannerTemp)
                .gender(gender)
                .build());

        // when
        Users findUser = userRepository.findOne(savedId);

        // then
        Assertions.assertThat(findUser.getEmail()).isEqualTo(email);
        Assertions.assertThat(findUser.getAgeRange()).isEqualTo(ageRange);
        Assertions.assertThat(findUser.getName()).isEqualTo(name);
        Assertions.assertThat(findUser.getMannerTemp()).isSameAs(mannerTemp);
        Assertions.assertThat(findUser.getGender()).isEqualTo(gender);
    }

}