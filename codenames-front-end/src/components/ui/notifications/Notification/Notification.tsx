import React, {CSSProperties, Dispatch, FC, useEffect, useState} from 'react';
import INotification from "../../../../models/notifications/INotification";

import css from "./Notification.module.css"

interface NotificationProps {
    deleteNotification: Dispatch<number>;
    notification: INotification;
    className?: string;
}

const Notification: FC<NotificationProps> = ({
                                                 notification,
                                                 deleteNotification,
                                                 className
                                             }) => {
    const [exit, setExit] = useState<boolean>(false);
    const [load, setLoad] = useState<number>(0);    // 0 - 100%
    const [interval, setIntervalState] = useState<number>();

    const handleStartTimer = () => {
        setIntervalState(setInterval(() => {
            setLoad((prev) => {
                if (prev < 100) {
                    return prev + 1;
                }

                clearInterval(interval);
                return prev;
            });
        }, 50))
    }
    const handlePauseTimer = () => {
        clearInterval(interval);
    }
    const handleCloseNotification = () => {
        handlePauseTimer();
        setExit(true);

        setTimeout(() => {
            deleteNotification(notification.id);
        }, 500);
    }

    useEffect(() => {
        handleStartTimer();
    },[]);

    useEffect(() => {
        if (load === 100) {
            handleCloseNotification();
        }
    }, [load])

    return (
        <div
            onMouseEnter={handlePauseTimer}
            onMouseLeave={handleStartTimer}
            className={[className, css.Notification, exit ? css.Exit : ""].join(" ")}
        >
            {notification.content}
            <div
                className={css.loadBar}
                style={{'--load-bar': `${load}%`} as CSSProperties}
            ></div>
        </div>
    );
};

export default Notification;