import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class AddStudentToProject extends React.Component{
    constructor(props) {
        super(props);
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            projectId:queryParams.get('projectId'),
            userId: '',
            nrIndeksu: '',
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
            method:'get',
            url:'http://localhost:8080/getStudentByNrIndeksu?nrIndeksu='+this.state.nrIndeksu,
        }).then(response => response.data)
            .then((data)=>{
                let tmp = data.studentId
                axios({
                    method:'get',
                    url:'http://localhost:8080/getStudentInProject?studentId='+data.studentId+
                    '&projectId='+this.state.projectId,
                }).then(response => response.data)
                    .then((data)=>{
                            if(data.studentId === undefined)
                                axios({
                                    method:'post',
                                    url:'http://localhost:8080/addStudentToProject?studentId='+tmp+
                                        '&projectId='+this.state.projectId,
                                }).then(()=>{
                                    window.location = "/lecturer/projects/studentList?projectId="+this.state.projectId;
                                });});});

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
                    <Button variant="primary" href={"/lecturer/projects/studentList?projectId="+this.state.projectId}>Powrót</Button>{' '}
                </Card.Header>
                <Form  onSubmit={this.submitChange}>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Podaj numer indeksu Studenta</Form.Label>
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

export default AddStudentToProject;