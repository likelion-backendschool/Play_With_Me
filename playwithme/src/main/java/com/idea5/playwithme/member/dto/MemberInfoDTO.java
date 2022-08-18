package com.idea5.playwithme.member.dto;

import com.idea5.playwithme.article.domain.Article;
import com.idea5.playwithme.comment.domain.Comment;
import com.idea5.playwithme.member.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private String ageRange;
    private String gender;
}
