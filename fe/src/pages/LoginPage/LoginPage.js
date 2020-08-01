import React, { useState } from 'react';
import { connect } from 'react-redux';
import { doLogin } from '../../shared/user/User.reducer';
import { useHistory } from 'react-router-dom';
import { Form, Image, Container, Row, Col } from 'react-bootstrap';
import NavBarComponent from '../../components/NavBar/NavBarComponent';
import FooterComponent from '../../components/Footer/FooterComponent';
import CustomButton from '../../components/Buttons/CustomButton';
import Loading from '../../components/Loading/Loading'
import './style.css';

function LoginPage(props) {
    const email = useFormInput(''); // semi-state
    const password = useFormInput(''); // semi-state
    const navigation = useHistory(); 

    function useFormInput (initialValue) {
        const [value, setValue] = useState(initialValue); // state
        function handleChange(e) {
            setValue(e.target.value);
        };
        return {
            value,
            onChange: handleChange
        };
    };

    function handleDoLogin(e) {
        e.preventDefault();
        if(email.value === '' && password.value === '') return alert("Campos vazios");
        else {
            props.doLogin(email.value, password.value, navigation);
        }
    };

    const { loading } = props;
    return(
        <>
            <NavBarComponent>
                <CustomButton 
                    to={"/register"}
                    title={"Cadastro"}
                />
                <CustomButton 
                    to={"/login"}
                    title={"Login"}
                />
            </NavBarComponent>
            <Container fluid className="login-container">
                <Row>
                    <Col sm={7} className="text-container">
                        <h3>Bem vindo ao portal da Biblioteca Steinfield !</h3>
                        <p>
                            No ramo  desde  o  século X,  agora  nossos  serviços  podem  ser  requisitados  pela  internet! 
                            Alugue  ou  compre  livros  e  busque  em  uma  das  nossas  franquias  ou requisite  um  delivery.
                        </p>
                    </Col>
                    <Col sm={4} className="form-container">
                        <Form onSubmit={handleDoLogin}>
                            <h1>LOGIN</h1>
                            <Form.Group controlId="formBasicEmail" >
                                <Form.Label>Email</Form.Label>
                                <Form.Control 
                                    {...email}
                                    type="email" 
                                    placeholder="Email" 
                                />
                            </Form.Group>
                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Senha</Form.Label>
                                <Form.Control 
                                    {...password}
                                    type="password" 
                                    placeholder="Senha" 
                                />
                            </Form.Group>
                            <Form.Group as={Row}>
                                <button 
                                    type="submit" 
                                    className="btn-login"
                                    disabled={loading}
                                > 
                                    { 
                                        !loading ? "Entrar" 
                                        :<Loading 
                                            txt={"Loading ..."}
                                            style={{marginRight: 10}}
                                        /> 
                                    }
                                </button>
                            </Form.Group>
                            <center>
                                <Image src="https://64.media.tumblr.com/beefdedff14135bcfd92ae4b58f67eaa/8dcff200d66f1b68-69/s500x750/886b24d40fce744ae9b835eb6ab968ee90e53b23.png" width="200" height="200" />
                            </center>
                        </Form>
                    </Col>
                </Row>
            </Container>
            <FooterComponent />
        </>
    );
}

const mapStateToProps = ({ userState }) => ({
    loading: userState.loading,
});

const mapDispathToProps = {
    doLogin
};

export default connect(mapStateToProps, mapDispathToProps)(LoginPage);