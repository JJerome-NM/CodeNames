import React, {FormEvent, useState} from 'react';
import {StyledBlueYellowBG} from "../../../components";
import {StyledSingUp} from "./SignUpStyles";
import {
    StyledAuthenticationButton,
    StyledAuthenticationFormTitle,
    StyledAuthenticationFrom, StyledAuthenticationInput,
    StyledAuthenticationInputBlock
} from "../AuthenticationStyles";
import useFetching from "../../../hooks/useFetching";
import {AxiosResponse} from "axios";
import {notify} from "../../../models";
import {Flip, ToastContainer} from "react-toastify";
import {authRequest, removeAuthToken, setAuthToken} from "../../../helper";
import {RestConfig} from "../../../config";

type SignUpResponse = {
    nickname: string;
    jwtToken: string;
    login: string;
}

const SingUp = () => {
    const [email, setEmail] = useState<string>("")
    const [nickname, setNickname] = useState<string>("")
    const [password, setPassword] = useState<string>("")
    const [repeatedPassword, setRepeatedPassword] = useState<string>("")

    const [tryRegister] = useFetching(async () => {
        removeAuthToken()
        const response: AxiosResponse<SignUpResponse> = await authRequest(
            "POST",
            RestConfig.paths.request.signUp,
            {
                email: email,
                nickname: nickname,
                password: password
            })


        setAuthToken(response.data.jwtToken)
        console.log(response.data)
    })

    const validateFormAndTryRegister = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        if (email.length < 4){
            return notify.error("Login length must be exceed 4 symbols")
        } else if (password.length < 8){
            return notify.error("Password length must be exceed 8 symbols")
        } else if (password !== repeatedPassword){
            return notify.error("Passwords must match")
        } else {
            tryRegister()
        }
    }


    return (
        <StyledSingUp>
            <StyledAuthenticationFrom
                onSubmit={validateFormAndTryRegister}
            >

                <StyledAuthenticationFormTitle marginBottom="4vh">
                    To get started, you need to sing up
                </StyledAuthenticationFormTitle>

                <StyledAuthenticationInputBlock>
                    <StyledAuthenticationInput
                        inputLabelText="Nickname"
                        placeholder="ArsenTop123"
                        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setNickname(e.target.value)}
                    />
                    <StyledAuthenticationInput
                        inputLabelText="Email"
                        placeholder="ArsenTop123@gmail.com"
                        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
                    />
                    <StyledAuthenticationInput
                        inputLabelText="Password"
                        type="password"
                        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
                    />
                    <StyledAuthenticationInput
                        inputLabelText="Password"
                        type="password"
                        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setRepeatedPassword(e.target.value)}
                    />
                </StyledAuthenticationInputBlock>
                <StyledAuthenticationButton>
                    Sign Up
                </StyledAuthenticationButton>

            </StyledAuthenticationFrom>

            <StyledBlueYellowBG/>
        </StyledSingUp>
    );
};

export default SingUp;