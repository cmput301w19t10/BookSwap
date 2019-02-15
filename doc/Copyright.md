Our app is used to help people borrow books from each other, and lend spare books that a owner doesn't need. While creating our UI and interactions we studied popular apps such as weChat and Uber.

Key terms and generalized rules are listed below (provisional 14/2/2019):   

#### Swap 
This is the job requested by the [Borrower](#r1) and fulfilled by the [Owner](#p1). There is one task, but not all of its attributes will be shown between the Owner and Borrower. A task will hold the following items:
1. _Owner name_ - name of Owner available to both Owner and Borrower.
1. _Borrower name_ - name of the Borrower avaiblabe to both the Owner and Borrower
1. _Title_  - Title of a book available to both Owner and Provider. Owner adds books with titles (mandatory). 
1. _Description_ - available to both Owner and Borrower.  Owner inputs. 
1. _Status_ - Special conditional state the boook is in. Available to both Owner and Borrower. Managed by system. 
1. _Address_ (optional) - Address of the meetup location available to both Owner and Borrower.  Owner inputs upon accepting. 
1. _GPS Location_ (optional) - GPS location available to both Owner and Borrower to be seen on Google Maps.  Owner inputs. 
1. _Location Comment_ (optional) - A comment string available to both the Owner and the Borrower. Owner inputs.

A Owner may:
   * Edit a Swap's location (Address, GPS Location, Location Comment)
   * Cancel a swap
   * Start a swap

A Borrower may: 
   * Confirm a swap is made

#### Book
A book is a/the object to be swaped. Only a owner can create books. A book has the following attributes.
1. _Book Owner_ - Automatically created when a owner puts a new book.
1. _Book Borrower_ Automatically generated when the book is borrowed.
1. _Book Title_ Title of book set by owner.
1. _Book Author_ Author of the book set by owner.
1. _Book Photo_ (optional) - a photo of a book set by owner.
1. _Book Barcode_ (optional) - optional barcode set by owner. 
1. _Book Description_ (optional) - Short description of the book
1. _Book Status_ -  status of a book set by the system
    1. _Available_ - status in which the books can be edited or deleted by owner. 
    1. _Requested_ - status of a book when one or more borrowers requests for a swap.
    1. _Accepted_ - status of a book when the owner accepts a swap request
    1. _Borrowed_ - status of a book when the book is borrowed by a borrower. 

#### Book Status
A book can be one of four states and will typically follow a transition of 'Available'->'Requested'->'Accepted'->'Borrowed'->'Available'. Details follow:  
1. _Available_ - Occurs when a book is available to be swapped. Indicates that there are no potential borrowers for this book. Borrowers may request to swap for this book at this time. The owner can edit or delete the book at this time.
1. _Requested_ - Occurs when a book is being requested by one or more borrowers. Borrowers may request for this book. Owners can deny borrowers, and can accept a single borrower. Upon accepting a single borrower, the rest are automatically denied.
1. _Accepted_ - Occurs when the owner accepts a swap request, and initiates a swap. Borrowers that are not part of the swap can not see this book at this time.
1. _Borrowed_ - Occurs when the book is borrowed by a borrower. 


#### User
A user is any user who is logged into our system. They can be in either of the two states:  
* <a name="r1">**Borrower**</a> -  A state in which the user can view books, reqest for available or requested books, and confirm swaps, and review owners.
*  <a name="p1">**Owner**</a> -  A state in which the user can create new books, edit existing books, accept and deny requests, set up swaps, and review borrowers.

A user will be in one of these two "states" at any given time, shown by the tab they are in at the bottom of any screen inside the app. 


* **Provider View**: The UI options and actions available to a user in the Task Provider state  
    * UI VIEWS
       * **Provider Task View**: The detailed UI and fields that show to the Task Borrower  
       * **Provider Assigned Task List View**: A list of tasks view that have been assigned to a task Borrower
       * **Provider Search List View**: A list of tasks view determined by a search
       * **Provider Map View"**: A map view where the Borrower can see requested tasks within 5km of his/her location
       * **Provider Profile View**: A view that shows the Requester information for the Borrower
        * **User Profile View:** The view which displays the user contact info
    * ACTIONS
       * **Place Bid:** The action item (button click) to place a bid on a task from the "Provider TaskList view". Only available in the Provider View.
       * **View User:** Action item to view profile of Task Requester
       * **Edit Profile:** Action item to edit user profile






* **Requester View**: The UI options and actions available to a user in the Task Requester state  
    * UI VIEWS
        * **Requester Task View** - The detailed UI and fields that show to the Task Owner
            * _Requester Task Requested view_ - detailed UI and fields when task is in 'Requested State'
            * _Requester Task Bidded view_ - detailed UI and fields when task is in 'Bidded State' 
            * _Requester Task Assigned view_ - detailed UI and fields when task is in 'Assigned State'
            * _Requester Task Done view_ - detailed UI and fields when task is in 'Done State'
        * **Requester BidList View** -  The list view of bids a task Owner has with status "Bidded"
        * **Requester AssignedList View** -  The list view of tasks a task Owner has with status "Assigned"
        * **Requester TaskList View** -  The list view of tasks a task Owner has with Status "Assigned" or "Bidded"
        * **Requester Profile View**: A view that shows the Profile information of the Provider, including the rating. 
        * **User Profile View:** The view which displays the user contact info
    * ACTIONS
        * **Add Task** The action item (button click) in lower menu to create a task. Only available in the Requester View.
        * **View Requested Tasks** The action item (button click) in lower menu to view tasklist. Only available in the Requester View.  
        * **Edit Task** The action item (button click) on each task in "Requested Tasklist View" or "Requester AssignedList View". Only available in the Requester View.
        * **Delete Task** The action item (button click) on each task in "Requested Tasklist View". Only available in the Requester View.
        * **Decline Bid** The action item (button click) on each bid in "Requester Task View". Only available in the Requester View.
        * **Accept Bid** The action item (button click) on each bid in "Requester Task View". Only available in the Requester View.
        * **Set Done** The action item (button click) on task "Requester Task View" when Task Status is Assigned. Only available in the Requester View.
        * **Set Requested** The action item (button click) on task "Requester Task View" when Task Status is Assigned. Only available in the Requester View.
        * **Select Task Map** - Action item (button click) on "Requester Task View" which allows the user to set/edit a geo-location for the task. 
        * **Review Provider** - Action item (button click) on "Requester Task View" when task is Set to "Done". This allows the user to review the Borrower
       * **View User:** Action item to view profile of Task Provider
       * **Edit Profile:** Action item to edit user profile

#### Map  
This allows a task to be represented with a marker on the map. It has three different types of access:  
* **Available Tasks Map** - This is all the tasks in status "Requested" or "Bidded" within 5km of the task Borrower.    
* **Select Task Map** - Available to the task Owner, in which they can select or edit the location of the task.  
* **Task View Map** - Allows task Owner or Borrower to view the location of the task.      

#### Wow Factor
To help build trust between our task Borrowers and Owners, we feature the following functionalities
* **Profile photos** - This allows a user to upload a photo what will be displayed next to their contact info.  
* **Rating and Reviews** - This allows the task Owner to review a task Borrowers after a task status has been set to "Done"  <br />
* We also added the functionality for Borrowers and Owners to see their completed tasks. 
* We added a splash screen to help create a better user experience
* We added logout functionality, and the ability for the users to not need to sign in every time. 
* We added a date parameter to the task, for when the Owner needs a task completed by a certain date


<br /> <br /> <br /> 

#### Copyright (C) 2019 Jia Chen Liu, Bowen Hu, Yun Cao, Yifu Chen, Hongbo Zhong, Chaoran Meng, Brian 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



