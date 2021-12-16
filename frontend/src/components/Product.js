const Product = ({ product }) => {
  return (
    <>
      <td>{product.id}</td>
      <td>{product.name}</td>
      <td>{product.productType}</td>
      <td>{product.provider}</td>
      <td>{product.stock}</td>
      <td>{product.purchasePrice}</td>
      <td>{product.retailPrice}</td>
      <td>
        <a href="#editProductModal" className="edit" data-toggle="modal">
          <i className="material-icons">&#xE254;</i>
        </a>
        <a href="#deleteProductModal" className="delete" data-toggle="modal">
          <i className="material-icons">&#xE872;</i>
        </a>
      </td>
    </>
  );
};

export default Product;
