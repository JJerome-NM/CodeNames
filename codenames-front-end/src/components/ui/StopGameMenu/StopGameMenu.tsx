import React, {FC} from 'react';
import css from "../../../pages/GameRoom/styles/main.module.css";

const StopGameMenu: FC = () => {
    return (
        <div className={css.StopGameMenu}>
            <div className={css.Team}>
                <div className={css.TeamName}>
                    Blue
                </div>

                <div className={css.TeamMaster}>
                    Master - JJerome
                </div>
                <div className={css.TeamPlayerBlock}>
                    <h3 className={css.TeamPlayerLabel}>Players</h3>
                    <ul className={css.TeamPlayers}>
                        <li>JJerome</li>
                        <li>JJerome</li>
                        <li>JJerome</li>
                        <li>JJerome</li>
                    </ul>
                </div>

            </div>
        </div>
    );
};

export default StopGameMenu;