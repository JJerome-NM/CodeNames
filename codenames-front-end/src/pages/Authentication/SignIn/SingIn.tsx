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
import {Flip, ToastContainer} from "react-toastify";
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

const SingIn = () => {
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

            if (window.history.state && window.history.state.idx > 0) {
                navigate(-1);
            } else {
                navigate('/room', { replace: true });
            }
        } catch (e){
            notify.error("Check the data you entered")
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

            <ToastContainer
                position="bottom-right"
                transition={Flip}
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick={false}
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="dark"
            />
        </StyledSignIn>
    );
};

export default SingIn;