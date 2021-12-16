import { useContext, useState } from "react";
import { ProductContext } from "../context/ProductContext";
import Product from "./Product";
import { Button, Modal } from "react-bootstrap";
import AddProductForm from "./AddProductForm";

const ProductList = () => {
  const { products } = useContext(ProductContext);

  const [show, setShow] = useState(false);

  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  return (
    <>
      <div className="table-title">
        <div className="row">
          <div className="col-sm-6">
            <h2>Manage Products</h2>
          </div>
          <div className="col-sm-6">
            <Button
              onClick={handleShow}
              href="#addProductModal"
              className="btn btn-success"
              data-toggle="modal"
            >
              <i className="material-icons">&#xE147;</i>
              <span>Add New Product</span>
            </Button>
          </div>
        </div>
      </div>

      <table className="table table-stripped table-hover">
        <thead>
          <tr>
            <th>Id</th>
            <th>Product Name</th>
            <th>Product Type</th>
            <th>Provider</th>
            <th>Stock</th>
            <th>Purchase Price</th>
            <th>Retail Price</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <Product product={product} />
            </tr>
          ))}
        </tbody>
      </table>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <AddProductForm />
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={handleClose} variant="secondary">
            Close Button
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default ProductList;
