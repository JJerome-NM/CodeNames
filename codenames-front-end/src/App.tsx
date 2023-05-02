import React from 'react';
import {privateRouters, publicRouters} from "./components/router";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          {publicRouters.map(route =>
              <Route
                  key={route.path}
                  path={route.path}
                  element={<route.component />}
              />
          )}
          {privateRouters.map(route =>
              <Route
                  key={route.path}
                  path={route.path}
                  element={<route.component />}
              />
          )}

          <Route
              path="*"
              element={<Navigate to="/room/connect" replace/>}
          />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
