import React from "react";
import {Jumbotron} from "react-bootstrap"


class Welcome extends React.Component{

    render() {
        return (
            <Jumbotron className = "bg-dark text-light">
                <h1>Witaj w serwisie do zarządania projektami!</h1>
                <p>
                    Zaloguj się jako wykładowca lub student..
                </p>

            </Jumbotron>
        );
    }
}

export default Welcome;