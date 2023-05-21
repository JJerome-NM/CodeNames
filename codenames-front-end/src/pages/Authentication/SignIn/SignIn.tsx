import React, {useState} from 'react';
import {StyledSignIn} from "./SignInStyles";
import {StyledBlueYellowBG} from "../../../components";
import {
    StyledAuthenticationButton,
    StyledAuthenticationFormTitle,
    StyledAuthenticationFrom, StyledAuthenticationInput,
    StyledAuthenticationInputBlock
} from "../AuthenticationStyles";
import useFetching from "../../../hooks/useFetching";
import {authRequest, removeAuthToken, setAuthToken} from "../../../helper";
import {RestConfig} from "../../../config";
import {notify} from "../../../models";
import {AxiosResponse} from "axios";
import {useNavigate} from "react-router-dom";

type SignInResponse = {
    nickname: string;
    jwtToken: string;
    login: string;
}

const SignIn = () => {
    const navigate = useNavigate();
    const [login, setLogin] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const [tryLogin] = useFetching(async () => {
        try {
            removeAuthToken()
            const response: AxiosResponse<SignInResponse> = await authRequest("POST", RestConfig.paths.request.signIn, {
                login: login,
                password: password
            })

            setAuthToken(response.data.jwtToken);

            navigate('/room', { replace: true });
            window.location.reload()
        } catch (e: any){
            notify.error(e.response.data.message)
        }
    })

    return (
        <StyledSignIn>

            <StyledAuthenticationFrom onSubmit={(e) => {
                e.preventDefault()
                tryLogin()
            }}>

                <StyledAuthenticationFormTitle marginBottom="4vh">
                    Sign In
                </StyledAuthenticationFormTitle>

                <StyledAuthenticationInputBlock>
                    <StyledAuthenticationInput
                        inputLabelText="Login"
                        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setLogin(e.target.value)}
                    />
                    <StyledAuthenticationInput
                        inputLabelText="Password"
                        type="password"
                        onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
                    />
                </StyledAuthenticationInputBlock>
                <StyledAuthenticationButton>Sign In</StyledAuthenticationButton>
            </StyledAuthenticationFrom>
            <StyledBlueYellowBG/>
        </StyledSignIn>
    );
};

export default SignIn;