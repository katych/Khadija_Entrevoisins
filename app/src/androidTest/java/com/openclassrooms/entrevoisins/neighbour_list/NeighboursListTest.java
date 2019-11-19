
package com.openclassrooms.entrevoisins.neighbour_list;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.Toast;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)

public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private List <Neighbour> mNeighbourList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    private  NeighbourApiService mApiService = DI.getNeighbourApiService();
    private List<Neighbour> mFavoriteList = mApiService.getFavoritesNeighbour();
    private  final int position = 0;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView (ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }


    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));



    }



    @Test
    public void clickItemAction_mustOpen_detailsActivity () {

        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        onView(ViewMatchers.withId(R.id.details_neighbours)).check(matches(isDisplayed()));


    }


    @Test
    public void userNameText_isNotEmpty (){

        Neighbour mNeighbour = mNeighbourList.get(position);
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(position,click()));
        onView(withId(R.id.neighbours_name)).check(matches(withText(mNeighbour.getName())));

    }

    @Test
    public void deleteFavoritesNeighbour_ifListNotEmpty() {

        mFavoriteList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Neighbour mNeighbour = mNeighbourList.get(i);
            mNeighbour.setFavorite(true);
            mFavoriteList.add(mNeighbour);
            onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            onView(withId(R.id.button_favorites)).perform(click());
            pressBack();
        }
        onView(withId(R.id.container)).perform(scrollRight());
        onView(withId(R.id.list_favorites_neighbours)).check(withItemCount(mFavoriteList.size()));
        onView(withId(R.id.list_favorites_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.list_favorites_neighbours)).check(withItemCount(mFavoriteList.size() - 1));


    }

    @Test
    public void listFavorites_containsJust_FavoritesNeighbours(){
        mFavoriteList=new ArrayList<>();

        for ( int i=0 ; i<4;i++ ) {
            Neighbour mNeighbour = mNeighbourList.get(i);
            mNeighbour.setFavorite(true);
            mFavoriteList.add(mNeighbour);
            onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            onView(withId(R.id.button_favorites)).perform(click());
            pressBack();

        }
        onView(withId(R.id.container)).perform(scrollRight());
        onView(withId(R.id.list_favorites_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.list_favorites_neighbours)).check(withItemCount(mFavoriteList.size()));

        }

    }




