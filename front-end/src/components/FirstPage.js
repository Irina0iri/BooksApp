import * as React from "react";
// import {Navigate} from "react-router-dom";
import NavBar from "./NavBar";

class FirstPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shouldNavigate: false
        }
    }

    render() {
        return (
            <div>
                <NavBar/>
                {this.state.shouldNavigate && (<NavBar/>)}
            </div>
        );
    }

}

export default FirstPage;