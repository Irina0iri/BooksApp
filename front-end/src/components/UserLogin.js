import axios from 'axios';
import {useState } from "react";
import "./UserRegistration.css";
import Button from '@mui/material/Button';
// import NavBar from "./NavBar";

function UserLogin() {

    const [email,setEmail] = useState('');
    const [password,setPassword] = useState('');


    const style = {

        border: "1px blue",
        width: "200px",
        borderRadius: "8px",
        padding: "8px",
        display: "flex",
        height: "80px",
        content: "center"
    }
    const buttonStyle = {
        width: "100px",
        borderRadius: "8px",
        padding: "8px",
        display: "flex",
        height: "30px",
        content: "center"
    }

    async function login(event) {
        event.preventDefault();
        try
        {
            // eslint-disable-next-line no-template-curly-in-string
            await axios.post("http://localhost:8084/user/login",
                {
                    email : email,
                    password : password,
                }
            );

            alert("Employee Login Successfully");
            setEmail(email);
            setPassword(password);

        }
        catch(err)
        {
            alert("User Login Failed");
        }
    }

    return (
        <div className="Container-fluid">
            <form>
                <h2>Login to your account</h2>
                <div  style={style} className="form-group">

                    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus
                           value={email}
                           onChange={(event) => {
                               setEmail(event.target.value);
                           }
                           }/>
                </div>

                <div style={style} className="form-group">
                    <input type="password" id="inputEmail" class="form-control" placeholder="Password" required autofocus
                           value={password}
                           onChange={(event) =>
                           {
                               setPassword(event.target.value);
                           }}/>
                </div>

                <div style={buttonStyle} className="myButton">
                    <Button variant="outlined" size="medium" onClick={login}>Login</Button>
                </div>
            </form>

        </div>
    );
}

export default UserLogin;
