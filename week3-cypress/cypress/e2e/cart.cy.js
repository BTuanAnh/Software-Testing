describe('Cart and Checkout Features - Saucedemo', () => {
  beforeEach(() => {
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
    cy.url().should('include', '/inventory.html');
  });

  it('should add the first product to the cart', () => {
    cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click(); // Selector ổn định hơn nếu site có data-test
    // Nếu không, dùng: cy.get('.inventory_item').first().contains('Add to cart').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');
  });

  it('should sort products by price low to high', () => {
    cy.get('.product_sort_container').select('lohi');
    cy.get('.inventory_item_price').first().should('have.text', '$7.99');
  });

  it('should remove a product from the cart', () => {
    // Add first
    cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
    cy.get('.shopping_cart_link').click();

    // Remove
    cy.get('[data-test="remove-sauce-labs-backpack"]').click(); // Hoặc .cart_button
    cy.get('.shopping_cart_badge').should('not.exist');
    cy.get('.cart_list .cart_item').should('have.length', 0);
  });

  it('should proceed to checkout overview after filling info', () => {
    // Add first
    cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click();
    cy.get('.shopping_cart_link').click();

    // Checkout flow
    cy.get('[data-test="checkout"]').click();
    cy.get('[data-test="firstName"]').type('John');
    cy.get('[data-test="lastName"]').type('Doe');
    cy.get('[data-test="postalCode"]').type('12345');
    cy.get('[data-test="continue"]').click();

    cy.url().should('include', '/checkout-step-two.html');
    cy.get('.title').should('have.text', 'Checkout: Overview');
  });
});