# Engine
The goal of this project is to provide an MVC framework for structuring OpenGL programs in Java, with game development in mind.

The Model interface and BaseModel class provide a hierarchical way of defining world elements and entities.

The View interface can be implemented to create a render behavior for a particular model, 
  or extending BaseView can be used to create a window and initialize input.

The Controller interface manages input bindings, 
  using InputBehavior and MouseBehavior interfaces to allow for modular controls.
