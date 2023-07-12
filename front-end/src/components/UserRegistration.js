import axios from 'axios';
import {useState } from "react";
import "./UserRegistration.css";
import Button from '@mui/material/Button';

function UserRegistration() {

    const [email,setEmail] = useState('');
    const [role,setRole] = useState('');
    const [password,setPassword] = useState('');
    const [firstName,setFirstName] = useState('');
    const [lastName,setLastName] = useState('');

    const style = {
        border: "1px blue",
            width: "200px",
            borderRadius: "8px",
            padding: "8px",
            display: "flex",
            height: "80px",
        content: "center"
    }

    async function save(event) {
        event.preventDefault();
        try
        {
            // eslint-disable-next-line no-template-curly-in-string
            await axios.post("http://localhost:8084/user/register",
                 {
                     email : email,
                     role : role,
                     password : password,
                     firstName : firstName,
                     lastName : lastName
                 }
        );

            alert("Employee Registation Successfully");
            setEmail(email);
            setRole(role);
            setPassword(password);
            setFirstName(firstName);
            setLastName(lastName);

        }
        catch(err)
        {
            // console.log(role,email);
            // console.log(err);
            alert("User Registation Failed");
        }
    }

    return (
        <div className="Container-fluid">

            <form>
                <h2>Register form</h2>
                <div  style={style} className="form-group">
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus
                           value={email}
                           onChange={(event) => {
                               setEmail(event.target.value);
                           }
                           }/>
                </div>
                <div style={style} className="form-group">
                    <input type="role" id="inputEmail" class="form-control" placeholder="User Role" required autofocus
                           value={role}
                           onChange={(event) =>
                           {
                               setRole(event.target.value);
                           }}/>
                </div>
                <div style={style} className="form-group">
                    <input type="password" id="inputEmail" class="form-control" placeholder="Password" required autofocus
                           value={password}
                           onChange={(event) =>
                           {
                               setPassword(event.target.value);
                           }}/>
                </div>
                <div style={style} className="form-group">
                    <input type="firstName" id="inputEmail" class="form-control" placeholder="FirstName" required autofocus
                           value={firstName}
                           onChange={(event) =>
                           {
                               setFirstName(event.target.value);
                           }}/>
                </div>
                <div style={style} className="form-group">
                    <input type="lastName" id="inputEmail" class="form-control" placeholder="LastName" required autofocus
                           value={lastName}
                           onChange={(event) =>
                           {
                               setLastName(event.target.value);
                           }}/>
                </div>
                <div style={style} className="myButton">
                    <Button variant="outlined" size="medium" onClick={save}>Register</Button>
                </div>
            </form>

        </div>
    );
}

export default UserRegistration;
