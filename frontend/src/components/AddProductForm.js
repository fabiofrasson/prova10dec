import { Button, Form } from "react-bootstrap";

import { ProductContext } from "../context/ProductContext";
import { useContext, useState } from "react";

const AddForm = () => {
  const { addProduct } = useContext(ProductContext);

  const [newProduct, setNewProduct] = useState({
    name: "",
    type: 0,
    provider: {},
    stock: 0,
    purchasePrice: 0.0,
    retailPrice: 0.0,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    addProduct(name, type, provider, stock, purchasePrice, retailPrice);
  };

  const onInputChange = (e) => {
    setNewProduct({ ...newProduct, [e.target.name]: e.target.value });
  };

  const { name, type, provider, stock, purchasePrice, retailPrice } =
    newProduct;

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group>
        <Form.Control
          type="text"
          placeholder="Name *"
          required
          name="name"
          value={name}
          onChange={(e) => onInputChange(e)}
        />
      </Form.Group>
      <Form.Group>
        <Form.Control
          type="text"
          placeholder="Product Type *"
          required
          name="type"
          value={type}
          onChange={(e) => onInputChange(e)}
        />
      </Form.Group>
      <Form.Group>
        <Form.Control
          type="text"
          placeholder="Provider *"
          required
          name="provider"
          value={provider}
          onChange={(e) => onInputChange(e)}
        />
      </Form.Group>
      <Form.Group>
        <Form.Control
          type="text"
          placeholder="Stock *"
          required
          name="stock"
          value={stock}
          onChange={(e) => onInputChange(e)}
        />
      </Form.Group>
      <Form.Group>
        <Form.Control
          type="text"
          placeholder="Purchase Price *"
          required
          name="purchasePrice"
          value={purchasePrice}
          onChange={(e) => onInputChange(e)}
        />
      </Form.Group>
      <Form.Group>
        <Form.Control
          type="text"
          placeholder="Retail Price *"
          required
          name="retailPrice"
          value={retailPrice}
          onChange={(e) => onInputChange(e)}
        />
      </Form.Group>
      <Button variant="success" type="submit" block>
        Add new Product
      </Button>
    </Form>
  );
};

export default AddForm;
