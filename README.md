# LifeManager

Manage your life with Life Manager. This app helps you fully grasp your financial management, goals, and time.

Currently, the app has four pages: Statistics, Financial Management, Goal Management, and Activity Management. It allows linking activities to goals, showing the purpose of each activity.

The app uses the Room database to store information and has two tables displayed in the RecyclerViews of the Activity and Goals pages. Additionally, ViewModel is used in some sections to transfer data between fragments. For example, when editing registered goals, information is passed to the ViewModel, which then updates the view with the modified data.

Most UI sections utilize Material3 for better design, and custom shapes have been created.

Previously, ViewPagers2 were used in fragments, but due to limitations, an alternative method is now used for filtering and displaying goals and activities.

Soon, a full version of the app will be released on the Iranian app market. 🚀
