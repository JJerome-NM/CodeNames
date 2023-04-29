import React, {FC, useCallback} from 'react';
import INotification from "../../../../models/notifications/INotification";

import css from "./NotificationBlock.module.css"
import Notification from "../Notification/Notification";

interface NotificationBlockProps {
    notificationsState: INotification[];
    setNotificationsState: React.Dispatch<React.SetStateAction<INotification[]>>;
    className?: string;
}

const NotificationBlock: FC<NotificationBlockProps> = ({
                                                           notificationsState,
                                                           setNotificationsState,
                                                           className
                                                       }) => {

    const deleteNotification = useCallback((noteID: number) => {
        setNotificationsState(notificationsState.filter(note => note.id !== noteID));
    }, [setNotificationsState, notificationsState])

    return (
        <div className={css.NotificationBlock}>
            {notificationsState.map(note =>
                <Notification
                    key={note.id}
                    notification={note}
                    deleteNotification={deleteNotification}
                />)}
        </div>
    );
};

export default NotificationBlock;