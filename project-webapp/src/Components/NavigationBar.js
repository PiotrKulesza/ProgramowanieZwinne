import React from "react";
import {Navbar,Nav} from "react-bootstrap";
import {Link} from "react-router-dom";

class NavigationBar extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            user: ''
        }
    }

    componentDidMount() {

        if ('LECTURER' === localStorage.getItem('typeOfUser') ) {
            window.location = "/lecturer"
        }else
        if ('STUDENT' === localStorage.getItem('typeOfUser') ) {
            window.location = "/student"
        }

    }

    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className={"navbar-brand"}>
                    Projekty
                </Link>
                <Nav className="mr-auto">
                    <Link to="/loginStudent" className={"nav-link"}>Logowanie Studenta</Link>
                    <Link to="/loginLecturer" className={"nav-link"}>Logowanie Wykładowcy</Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;