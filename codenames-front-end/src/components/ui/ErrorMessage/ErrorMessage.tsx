import React, {FC, useEffect} from 'react';

import css from "./ErrorMessage.module.css"
import Cross from "../Cross/Cross";

interface ErrorMessageProps {
    showError: boolean;
    onErrorClose?: () => void;
    className?: string;
    children?: React.ReactNode;
}

const ErrorMessage: FC<ErrorMessageProps> = ({
                                                 showError,
                                                 onErrorClose,
                                                 className,
                                                 children
                                             }) => {

    const [timeout, setTimeoutState] = React.useState<number>();

    const close = () => {
        clearTimeout(timeout);
        onErrorClose && onErrorClose();
    }

    useEffect(() => {
        console.log(timeout)
        timeout && clearTimeout(timeout);
        if (showError) {
            setTimeoutState(
                setTimeout(() => {
                close();
            }, 5000));
        }
    }, [showError])

    return (
        <div className={[showError ? css.Show : "", className, css.ErrorMessage].join(" ")}>
            <div className="">
                {children}
            </div>
            <button
                className={css.CloseButton}
                onClick={close}
            >
                <Cross className={css.CloseButtonCross} color={"#C30000"}/>
            </button>
        </div>
    );
};

export const toggleError = (
    setErrorState: React.Dispatch<React.SetStateAction<any>>,
    setErrorText: React.Dispatch<React.SetStateAction<any>>,
    errorMessage: string
) => {
    setErrorState(false);
    setErrorText(errorMessage)
    setErrorState(true);
}

export default ErrorMessage;