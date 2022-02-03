import React from "react";
import {Navbar,Nav} from "react-bootstrap";
import {Link} from "react-router-dom";

class NavigationBarStudent extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
        }
    }

    componentDidMount() {

        if ('LECTURER' === localStorage.getItem('typeOfUser') ) {
            window.location = "/lecturer"
        }

    }

    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={"/student"} className={"navbar-brand"}>
                    Student
                </Link>
                <Nav className="mr-auto">
                    <Link to="/student/profile" className={"nav-link"}>Profil Użytkownika</Link>
                    <Link to="/student/projects" className={"nav-link"}>Moje projekty</Link>
                    <Link to="/logout" className={"nav-link"} style={{ marginRight: "auto" }}>Wyloguj</Link>
                </Nav>
            </Navbar>
        );
    }

}

export default NavigationBarStudent;