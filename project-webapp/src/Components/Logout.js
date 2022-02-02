import React from "react";
import {Navbar} from "react-bootstrap";

class Logout extends React.Component{

    componentDidMount(){
        localStorage.clear();
        window.location = "/"
    }

    render() {
        return (
            <Navbar bg="light" variant="light">
            </Navbar>
        );
    }
}

export default Logout;