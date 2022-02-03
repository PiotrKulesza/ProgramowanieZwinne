import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class EditTaskName extends React.Component{
    constructor(props) {
        super(props);
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            projectId:queryParams.get('projectId'),
            taskId:queryParams.get('taskId'),
            userId: '',
            nazwa: '',
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
            method:'put',
            url:'http://localhost:8080/updateTask?nazwa='+this.state.nazwa+'&taskId='+this.state.taskId,
        }).then(()=>{
                window.location = "/"+this.state.userType+"/projects/taskList/editTask?projectId="+this.state.projectId
                    +"&taskId="+this.state.taskId;
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
                    <Button size="sm" href={"/"+this.state.userType+"/projects/taskList/editTask?projectId="+this.state.projectId
                    +"&taskId="+this.state.taskId} >
                        Powrót
                    </Button>
                </Card.Header>
                <Form  onSubmit={this.submitChange}>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Nowa nazwa zadania</Form.Label>
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
                    </Card.Body>
                    <Card.Footer>
                        <div >
                            <Button size="sm" variant="success" type="submit" style={{"textAlign":"center"}}>
                                Zmień
                            </Button>{" "}
                        </div>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}

export default EditTaskName;