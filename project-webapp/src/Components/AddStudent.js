import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class AddProject extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            imie: '',
            nazwisko: '',
            nrIndeksu: '',
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
                        url:'http://localhost:8080/postStudent?loginId='+data.loginId+'&imie='+this.state.imie
                            +'&nazwisko='+this.state.nazwisko+'&nrIndeksu='+this.state.nrIndeksu,
                    }).then(()=>{
                        window.location = "/lecturer/studentList";
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
                <Card.Header>
                    <Button variant="primary" href={"/lecturer/studentList"}>Powrót</Button>{' '}
                </Card.Header>
                <Form  onSubmit={this.submitChange}>

                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Imię studenta</Form.Label>
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
                                <Form.Label>Nazwisko studenta</Form.Label>
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
                                <Form.Label>Numer Indeksu studenta</Form.Label>
                                <Form.Control
                                    required
                                    type="text"
                                    autoComplete={"off"}
                                    name={"nrIndeksu"}
                                    value={this.state.nrIndeksu}
                                    onChange={this.valueChange}
                                    placeholder="nrIndeksu"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>

                            <Form.Group as={Col}>
                                <Form.Label>Email studenta</Form.Label>
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

export default AddProject;