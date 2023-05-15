import React, {FormEvent} from "react";


type CNBlurredFromProps = {
    action?: string;
    method?: string;
    onSubmit?: (e: FormEvent<HTMLFormElement>) => void;
    className?: string;
    children?: React.ReactNode;
}

const CNBlurredFrom = ({
                           action,
                           method,
                           onSubmit,
                           className,
                           children
                       }: CNBlurredFromProps) => (
    <form
        className={className}
        action={action}
        method={method}
        onSubmit={onSubmit}
    >
        {children}
    </form>
)

export default CNBlurredFrom;