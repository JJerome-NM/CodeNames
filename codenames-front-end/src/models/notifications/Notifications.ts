import {toast, ToastOptions} from "react-toastify";
import React from "react";


const defaultToastOptions: ToastOptions = {
    position: "bottom-right",
    autoClose: 5000,
    hideProgressBar: false,
    closeOnClick: false,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
    theme: "dark",
}

export const notify = {
    success: (text: React.ReactNode) => {
        toast.success(text, defaultToastOptions);
    },

    default: (text: React.ReactNode) => {
        toast(text, defaultToastOptions);
    },

    error: (text: React.ReactNode) => {
        toast.error(text, defaultToastOptions);
    },

    warn: (text: React.ReactNode) => {
        toast.warn(text, defaultToastOptions);
    },

    info: (text: React.ReactNode) => {
        toast.info(text, defaultToastOptions);
    }
}
