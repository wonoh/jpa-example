package com.wonoh.spring.jpa.member;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
@Builder
public class MemberRequestDto {


    @Min(value = 0,message = "나이는 0보다 커야합니다.")
    private int age;

    @NotBlank(message = "이름을 작성해주세요.") @NotEmpty(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "이메일을 작성해주세요.") @NotEmpty(message = "이메일을 작성해주세요.")  @Email(message = "메일의 양식을 지켜주세요.")
    private String email;

    @NotBlank(message = "팀이름을 작성해주세요.") @NotEmpty(message = "팀이름을 작성해주세요.")
    private String teamName;

    @NotBlank(message = "성별을 작성해주세요.") @NotEmpty(message = "성별을 작성해주세요.")
    private String gender;

    @Min(value = 52,message = "52미만은 선수로 등록할수 없습니다.")
    private int weight;


    public Member toEntity(){

        Gender gender;

        if(this.gender.equals("남")){
            gender = Gender.MAIL;
        }else{
            gender = Gender.FEMALE;
        }

        Weight weight;

        if(this.weight >= 92){
            weight = Weight.HEAVY;
        }else if(this.weight >= 83){
            weight = Weight.LIGHT_HEAVY;
        }else if(this.weight >= 77){
            weight = Weight.MIDDLE;
        }else if(this.weight >= 70){
            weight = Weight.WELTER;
        }else if(this.weight >= 65){
            weight = Weight.LIGHT;
        }else if(this.weight >= 61){
            weight = Weight.FEATHER;
        }else if(this.weight >= 56){
            weight = Weight.BANTAM;
        }else{
            weight = Weight.FLY;
        }

        return Member.builder()
                .age(age)
                .email(email)
                .gender(gender)
                .weight(weight)
                .name(name)
                .build();
    }
}
