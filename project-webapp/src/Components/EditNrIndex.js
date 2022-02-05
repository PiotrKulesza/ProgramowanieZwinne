import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class EditName extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
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
            method:'put',
            url:'http://localhost:8080/updateStudent/'+this.state.userId+'?nrIndeksu='+this.state.nrIndeksu,
        }).then(()=>{
                window.location = "/student/profile";
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
                <Card.Header>
                    <Button size="sm" href={"/"+this.state.userType+"/profile"} >
                        Powrót
                    </Button>
                </Card.Header>
                <Form  onSubmit={this.submitChange}>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Nowy numer indeksu użytkonwika</Form.Label>
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
                                Zmień
                            </Button>{" "}
                        </div>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}

export default EditName;