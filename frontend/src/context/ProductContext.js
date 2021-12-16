import { createContext, useState } from "react";

export const ProductContext = createContext();

const ProductContextProvider = (props) => {
  const [products, setProducts] = useState([
    {
      id: "bcdc2c08-469a-4d64-8dbe-99900427cd5a",
      name: "Product A",
      productType: "Food",
      provider: "Provider A",
      stock: 10,
      purchasePrice: "$ 10.00",
      retailPrice: "$ 15.00",
    },
    {
      id: "b7f6bc55-b728-4d96-9e8b-a932fce9f4ba",
      name: "Product B",
      productType: "Food",
      provider: "Provider B",
      stock: 15,
      purchasePrice: "$ 10.00",
      retailPrice: "$ 15.00",
    },
    {
      id: "f0d547d3-127b-40f7-8e85-f2fa45e2ddb8",
      name: "Product C",
      productType: "Food",
      provider: "Provider C",
      stock: 20,
      purchasePrice: "$ 10.00",
      retailPrice: "$ 15.00",
    },
    {
      id: "5ec6d852-9446-4615-bea7-a5efd4d5667f",
      name: "Product D",
      productType: "Food",
      provider: "Provider D",
      stock: 25,
      purchasePrice: "$ 10.00",
      retailPrice: "$ 15.00",
    },
    {
      id: "72b5896e-17f1-43a8-bcb4-8df9b7a1d3a1",
      name: "Product E",
      productType: "Food",
      provider: "Provider E",
      stock: 30,
      purchasePrice: "$ 10.00",
      retailPrice: "$ 15.00",
    },
  ]);

  const addProduct = (
    name,
    productType,
    provider,
    stock,
    purchasePrice,
    retailPrice
  ) => {
    setProducts({
      name,
      productType,
      provider,
      stock,
      purchasePrice,
      retailPrice,
    });
  };

  return (
    <ProductContext.Provider value={{ products, addProduct }}>
      {props.children}
    </ProductContext.Provider>
  );
};

export default ProductContextProvider;
