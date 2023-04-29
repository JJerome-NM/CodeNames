import {ReactNode} from "react";

interface INotification{
    id: number;
    type: string;
    content: ReactNode;
}

export default INotification;