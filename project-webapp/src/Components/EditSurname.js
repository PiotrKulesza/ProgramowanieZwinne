import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class EditSurname extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            nazwisko: '',
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

        let buildUrl = ''
        if(this.state.userType==="student")
            buildUrl='updateStudent'
        else
            buildUrl='updateLecturer'

        axios({
            method:'put',
            url:'http://localhost:8080/'+buildUrl+'/'+this.state.userId+'?nazwisko='+this.state.nazwisko,
        }).then(()=>{
                window.location = "/"+this.state.userType+"/profile";
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
                    <Button size="sm" href={"/"+this.state.userType+"/profile"} >
                        Powrót
                    </Button>
                </Card.Header>
                <Form  onSubmit={this.submitChange}>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Nowe imię użytkonwika</Form.Label>
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

export default EditSurname;