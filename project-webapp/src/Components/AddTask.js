import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class AddTask extends React.Component{
    constructor(props) {
        const queryParams = new URLSearchParams(window.location.search);
        super(props);
        this.state = {
            projectId:queryParams.get('projectId'),
            userId: '',
            nazwa: '',
            opis: '',
            userType:''
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
            url:'http://localhost:8080/addTask?projectId='+this.state.projectId
                +'&nazwa='+this.state.nazwa+'&opis='+this.state.opis,
        }).then(()=>{
                window.location = "/"+this.state.userType+"/projects/taskList?projectId="+this.state.projectId;
            }
        )
        ;

        this.forceUpdate();
        event.preventDefault();
    }

    componentDidMount() {
        this.setState({userId:localStorage.getItem('loggedUser')})
        if ('STUDENT' === localStorage.getItem('typeOfUser')) {
            this.setState({userType:"student"})

        }else{
            this.setState({userType:"lecturer"})

        }
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    <Button variant="primary" href={"/"+this.state.userType+"/projects/taskList?projectId="+this.state.projectId}>Powrót</Button>{' '}
                </Card.Header>
                <Form  onSubmit={this.submitChange}>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Nazwa Zadania</Form.Label>
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
                                <Form.Label>Opis Zadania</Form.Label>
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

export default AddTask;