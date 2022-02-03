import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class AddProject extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            nazwa: '',
            opis: ''
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
            url:'http://localhost:8080/addProject?lecturerId='+this.state.userId+'&opis='+this.state.opis
                +'&nazwa='+this.state.nazwa,
        }).then(()=>{
                window.location = "/lecturer/projects";
            }
        )
        ;

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
                    <Card.Header>
                        <Button variant="primary" href={"/lecturer/projects"}>Powrót</Button>{' '}
                    </Card.Header>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Nazwa Projektu</Form.Label>
                                <Form.Control
                                    required
                                    type="text"
                                    autoComplete={"off"}
                                    name={"nazwa"}
                                    value={this.state.nazwa}
                                    onChange={this.valueChange}
                                    placeholder="nazwa"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Opis Projektu</Form.Label>
                                <Form.Control
                                    required
                                    type="text"
                                    autoComplete={"off"}
                                    name={"opis"}
                                    value={this.state.opis}
                                    onChange={this.valueChange}
                                    placeholder="opis"
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