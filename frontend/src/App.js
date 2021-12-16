import ProductList from "./components/ProductList";
import ProductContextProvider from "./context/ProductContext";

function App() {
  return (
    <div className="container-xl">
      <div className="table-responsive">
        <div className="table-wrapper">
          <ProductContextProvider>
            <ProductList />
          </ProductContextProvider>
        </div>
      </div>
    </div>
  );
}

export default App;
