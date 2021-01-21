# fampayassignment

## Working Demo

<img src="https://user-images.githubusercontent.com/24780524/105339755-114f5080-5c03-11eb-8202-aa13baa32feb.gif" width=360>


## Usage

1) soution is entirely in different module `famCardContainer`

2) include the module in your project

3) add it in the `build.gradle` file of `app module`
  ``` 
      implementation project(":famCardContainer")
  ```
4) Add `famCardContainer` in activity/fragment

```
    <io.rotlabs.famcardcontainer.ui.FamCardContainer
        android:id="@+id/famContainer"
        android:background="@color/colorGray"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

5) In activity/fragment load the url
```
    famContainer.load(apiUrl)
```

## Deliveribles Done
- [X] Standalone container to load and display
- [X] Implement swipe down to refresh feature
- [X] On long press of Big Display Card (HC3), it should slide to the right and display action buttons 
    - [X] On tapping "remind later" action on a card, it should be removed from the display. This card should be shown on the next app start.
    - [X] On tapping "dismiss now" action, it should be removed from the display. This card should never be visible again.
- [X] Implement is_scrollable - if this property is true, then the card group is shown as a horizontal scroll view. If this property is false, then all cards are accommodated into the screen width itself with equal width for each card.

 
