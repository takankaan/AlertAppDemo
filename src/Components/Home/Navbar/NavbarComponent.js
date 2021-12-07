import React from 'react';
import { Link } from 'react-router-dom';

import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'reactstrap';

export default class NavbarComponent extends React.Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false
    };
    console.log(this.props.user)
  }

  componentDidUpdate() {
    
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  logout = () => {
    localStorage.clear()
  }

  render() {
    const baseURL = "/home" ;
    return (
      <div>
        <Navbar color="light" light expand="md">
          <NavbarBrand href={baseURL}> Hoşgeldiniz {this.props.user} </NavbarBrand>
          <NavbarToggler onClick={this.toggle} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar> 
              <NavItem>
                <NavLink href="/components/">Components</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
              </NavItem>
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  Menü
                </DropdownToggle>
                <DropdownMenu right>
                  <DropdownItem>
                    <Link to ="alerts" style={{textDecoration: "none",color:'black'}}> Alarmlarım </Link>
                  </DropdownItem>
                  <DropdownItem>
                  <Link to="profile" style={{textDecoration: "none",color:'black'}}>Profil Bilgilerim</Link>  
                  </DropdownItem>
                  <DropdownItem divider />
                  <DropdownItem onClick = {this.logout} href="/">
                    Çıkış Yap
                  </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
  }
}