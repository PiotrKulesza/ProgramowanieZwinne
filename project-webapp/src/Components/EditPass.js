import React from "react";
import axios from 'axios';
import {Alert, Button, Card, Col, Form} from "react-bootstrap";

class EditPass extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            oldPass:'',
            newPass:'',
            newPassRep:'',
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

        axios({
            method:'get',
            url:'http://localhost:8080/'+this.state.userType+'/'+this.state.userId,
        }).then(response => response.data)
            .then((data)=>{
                axios({
                    method:'put',
                    url:'http://localhost:8080/loginUpdate?loginId='+data.login.loginId+'&oldPass='
                        +this.state.oldPass+'&newPass='+this.state.newPass,
                }).then(()=>{

                        window.location = "/"+this.state.userType+"/profile";
                    }
                );
            });



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
                                <Form.Label>Stare Hasło</Form.Label>
                                <Form.Control
                                    required
                                    type="password"
                                    autoComplete={"off"}
                                    name={"oldPass"}
                                    value={this.state.oldPass}
                                    onChange={this.valueChange}
                                    placeholder="password"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} >
                                <Form.Label>Nowe hasło</Form.Label>
                                <Form.Control
                                    required
                                    type="password"
                                    autoComplete={"off"}
                                    name={"newPass"}
                                    value={this.state.newPass}
                                    onChange={this.valueChange}
                                    placeholder="password"
                                    className={"bg-dark text-white"}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} >
                                <Form.Label>Powtórz nowe hasło</Form.Label>
                                <Form.Control
                                    required
                                    type="password"
                                    autoComplete={"off"}
                                    name={"newPassRep"}
                                    value={this.state.newPassRep}
                                    onChange={this.valueChange}
                                    placeholder="password"
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
                    <CheckIfNotMatch newPass={this.state.newPass} newPassRep={this.state.newPassRep}/>

                </Form>
            </Card>
        );
    }
}

export default EditPass;

const CheckIfNotMatch = (props) =>{
    if(props.newPass!==props.newPassRep){
        return <AlertIfPassNotMatch />
    }else {
        return <EmptyDiv/>
    }
}

const AlertIfPassNotMatch = () => {
    return<div>
        <Alert  variant={"danger"}>
            Hasła nie są identyczne
        </Alert></div>
}

const EmptyDiv = () => {
    return<div>
    </div>
}