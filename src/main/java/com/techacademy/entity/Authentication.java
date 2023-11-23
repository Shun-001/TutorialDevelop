package com.techacademy.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** ログインユーザー名 */
    @Id
    private String loginUser;

    /** パスワード */
    private String password;

    /** 有効日付 */
    private Date validDate;

    /** ユーザーID */
    // @OneToOneは認証（Authentication）エンティティとユーザ（User）エンティティが1対1の関係であることを示す
    // @JoinColumnはリレーションを行なう項目を定義します。name が結合元（認証エンティティ）の項目名、 referencedColumnName が結合先（ユーザエンティティ）の項目名
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
