import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class AddLecturer extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            imie: '',
            nazwisko: '',
            email: ''
        };
        this.valueChange = this.valueChange.bind(this)
        this.submitChange = this.submitChange.bind(this)
    }

    valueChange  (event){
        this.setState({
            [event.target.name]:event.target.value
        })
    }

    submitChange (event) {


        axios({
            method:'POST',
            url:'http://localhost:8080/postLogin?email='+this.state.email,
        }).then(()=>{
            axios({
                method:'get',
                url:'http://localhost:8080/getLoginByEmail?email='+this.state.email,
            }).then(response => response.data)
                .then((data)=> {
                    axios({
                        method:'POST',
                        url:'http://localhost:8080/postLecturer?loginId='+data.loginId+'&imie='+this.state.imie
                            +'&nazwisko='+this.state.nazwisko,
                    }).then(()=>{
                        window.location = "/lecturer";
                    });

                });
        });

        this.forceUpdate();
        event.preventDefault();
    }

    componentDidMount() {
        this.setState({userId:localStorage.getItem('loggedUser')})
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Form  onSubmit={this.submitChange}>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Imię Wykładowcy</Form.Label>
                                <Form.Control
                                    required
                                    type="text"
                                    autoComplete={"off"}
                                    name={"imie"}
                                    value={this.state.imie}
                                    onChange={this.valueChange}
                                    placeholder="imie"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>

                            <Form.Group as={Col}>
                                <Form.Label>Nazwisko Wykładowcy</Form.Label>
                                <Form.Control
                                    required
                                    type="text"
                                    autoComplete={"off"}
                                    name={"nazwisko"}
                                    value={this.state.nazwisko}
                                    onChange={this.valueChange}
                                    placeholder="nazwisko"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Email Wykładowcy</Form.Label>
                                <Form.Control
                                    required
                                    type="email"
                                    autoComplete={"off"}
                                    name={"email"}
                                    value={this.state.email}
                                    onChange={this.valueChange}
                                    placeholder="email"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer>
                        <div >
                            <Button size="sm" variant="success" type="submit" style={{"textAlign":"center"}}>
                                Dodaj
                            </Button>{" "}
                        </div>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}

export default AddLecturer;