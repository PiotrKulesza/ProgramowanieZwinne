import React from "react";
import {Button, Form, Card, Col} from "react-bootstrap";
import axios from 'axios';

class LoginStudent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email:'',
            password:'',
            student:{},
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
            url: 'http://localhost:8080/students/getStudentByLogin?email=piokul003@pbs.edu.pl&password=1245'
        }).then(response => response.data)
            .then((data)=>{
                this.setState({student:data});

                if ('null' !== this.state.student.student_id && typeof this.student.studentId !== "undefined") {
                        localStorage.setItem('loggedUser', this.student.studentId);
                        localStorage.setItem('typeOfUser', 'STUDENT');
                        window.location = "/student/";
                }
                else {
                    this.state.textError="Podano niewałaściwy email albo hasło.";
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

export default LoginStudent;