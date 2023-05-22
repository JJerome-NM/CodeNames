import React, {useEffect, useRef} from 'react';
import {privateRouters, publicRouters} from "./router";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import useFetching from "./hooks/useFetching";
import {authRequest, getAuthToken, removeAuthToken} from "./helper";
import {RestConfig} from "./config";
import {AxiosResponse} from "axios";
import {Flip, ToastContainer} from "react-toastify";
import {UserAuthRole} from "./models/CodeNames/UserAuthRole";
import {notify} from "./models";

function App() {
    const isAuthorized = useRef<boolean>(!!getAuthToken())

    const [checkUserIsAuth, isLoading, error] = useFetching(async () => {
        try {
            const response: AxiosResponse<UserAuthRole> = await authRequest("GET", RestConfig.paths.request.whoami, {})

            if (response.data === UserAuthRole.USER || response.data === UserAuthRole.ADMIN) {
                isAuthorized.current = true
            } else if (response.data === UserAuthRole.GUEST) {
                notify.info("You are logged in as a GUEST, which may limit your possibilities")
            }
        } catch (e) {
            removeAuthToken();
        }
    })

    useEffect(() => {
        checkUserIsAuth();
    }, [])

    return (
        <BrowserRouter>
            <Routes>
                {publicRouters.map(route =>
                    <Route
                        key={route.path}
                        path={route.path}
                        element={<route.component/>}
                    />
                )}
                {isAuthorized.current && privateRouters.map(route =>
                    <Route
                        key={route.path}
                        path={route.path}
                        element={<route.component/>}
                    />
                )}
                <Route
                    path="*"
                    element={<Navigate to={isAuthorized.current ? "/room" : "/sign_in"} replace/>}
                />
            </Routes>
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
        </BrowserRouter>
    );
}

export default App;
