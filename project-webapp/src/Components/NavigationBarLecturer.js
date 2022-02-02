import React from "react";
import {Navbar,Nav} from "react-bootstrap";
import {Link} from "react-router-dom";

class NavigationBarLecturer extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
        }
    }

    componentDidMount() {

        if ('STUDENT' === localStorage.getItem('typeOfUser') ) {
            window.location = "/student"
        }

    }

    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={"/lecturer"} className={"navbar-brand"}>
                    Wykładowca
                </Link>
                <Nav className="mr-auto">
                    <Link to="/lecturer/profile" className={"nav-link"}>Profil Uzytkonwika</Link>
                    <Link to="/lecturer/projects" className={"nav-link"}>Projekty</Link>
                    <Link to="/lecturer/newLecturer" className={"nav-link"}>Dodaj wykładowcę</Link>
                    <Link to="/lecturer/studentList" className={"nav-link"}>Studenci</Link>
                    <Link to="/logout" className={"nav-link"} style={{ marginRight: "auto" }}>Wyloguj</Link>
                </Nav>
            </Navbar>
        );
    }

}

export default NavigationBarLecturer;