import React, {useEffect, useRef, useState} from 'react';
import {privateRouters, publicRouters} from "./router";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import useFetching from "./hooks/useFetching";
import {authRequest, getAuthToken} from "./helper";
import {RestConfig} from "./config";
import {AxiosResponse} from "axios";

function App() {
    const isAuthorized = useRef<boolean>(!!getAuthToken())
    const [redirectPath, setRedirectPath] = useState<string>("/sign_in")
    const [checkUserIsAuth] = useFetching(async () => {
        try {
            const response: AxiosResponse<boolean> = await authRequest("GET", RestConfig.paths.request.userIsAuth, {})

            if (response.data) {
                isAuthorized.current = true
            }
        } catch (e) {
            console.log(e)
        }
    })


    useEffect(() => {
        checkUserIsAuth()
    }, [])

    useEffect(() => {

        if (isAuthorized.current){
            setRedirectPath("/room")
        }
    }, [isAuthorized])


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
                {isAuthorized.current && privateRouters.map(route => {
                        return (<Route
                                key={route.path}
                                path={route.path}
                                element={<route.component/>}
                            />
                        )
                    }
                )}

                <Route
                    path="*"
                    element={<Navigate to={redirectPath} replace/>}
                />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
