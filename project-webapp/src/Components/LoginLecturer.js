import React from "react";
import {Button, Form, Card} from "react-bootstrap";
import axios from 'axios';

class LoginLecturer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email:'',
            password:'',
            lecturer:{},
            textError:""
        }
        this.valueChange = this.valueChange.bind(this)
        this.submitLogin = this.submitLogin.bind(this)
    }

    valueChange(event){
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    submitLogin(event){
        console.log(this.state.email)
        axios({
            method:'get',
            url: 'http://localhost:8080/getLecturerByLogin?email='+this.state.email+'&password='+this.state.password
        }).then(response => response.data)
            .then((data)=>{
                this.setState({lecturer:data});
                console.log(this.state.lecturer.lecturerId)
                if ('null' !== this.state.lecturer.lecturerId && typeof this.state.lecturer.lecturerId !== "undefined") {
                    localStorage.setItem('loggedUser', this.state.lecturer.lecturerId);
                    localStorage.setItem('typeOfUser', 'LECTURER');
                    window.location = "/student";
                }
                else {
                    this.setState({textError:"Podano niewałaściwy email albo hasło."});

                    this.forceUpdate();
                }
            });
        event.preventDefault();
    }

    render() {
        return(
            <Card className="border border-dark bg-dark text-light">
                <Card.Header>Zaloguj się</Card.Header>
                <Form onSubmit={this.submitLogin}>
                    <Card.Body>
                        <Form.Group>
                            <Form.Label>Email address</Form.Label>
                            <Form.Control
                                required
                                type="email"
                                name={"email"}
                                value={this.state.email}
                                onChange={this.valueChange}
                                placeholder="name@example.com"
                                className={"bg-dark text-light"}
                            />
                            <Form.Text id = {"emailTextError"}>
                                <p>{this.state.textError}</p>
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                required
                                type="password"
                                name={"password"}
                                value={this.state.password}
                                onChange={this.valueChange}
                                placeholder="Password"
                                className={"bg-dark text-light"}
                            />
                        </Form.Group>
                    </Card.Body>
                    <Card.Footer style={{"textAlign":"right"}}>
                        <Button size="sm" variant="success" type="submit">
                            Zaloguj
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        )
    }


}

export default LoginLecturer;