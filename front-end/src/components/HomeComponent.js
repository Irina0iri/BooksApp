import React from "react";
import {useParams, useNavigate} from "react-router-dom";

const HomeComponent = (props) => {
    const navigate = useNavigate();
    const params = useParams();

    const userName = params.userName ?? 'Default User';

    return (
        <div>
            <h3>{`Hello, ${userName}!`}</h3>
            <h1>Welcome to my page!</h1>
            <h2>This is my personal portofolio website!</h2>
            <button onClick={() => navigate('/about')}>Go to about section</button>
        </div>
    )
};

export default HomeComponent;