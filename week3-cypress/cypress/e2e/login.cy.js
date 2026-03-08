describe('Login Functionality - Saucedemo', () => {
  beforeEach(() => {
    cy.visit('https://www.saucedemo.com');
  });

  it('should login successfully with valid credentials', () => {
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
    cy.url().should('include', '/inventory.html');
    cy.get('.title').should('have.text', 'Products');
  });

  it('should show error message with invalid credentials', () => {
    cy.get('#user-name').type('invalid_user');
    cy.get('#password').type('wrong_password');
    cy.get('#login-button').click();
    cy.get('[data-test="error"]').should('be.visible')
      .and('contain.text', 'Username and password do not match any user in this service');
  });
});