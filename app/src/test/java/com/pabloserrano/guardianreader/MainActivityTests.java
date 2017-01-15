package com.pabloserrano.guardianreader;

// TODO

/*
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTests {

    private static final int ANY_NUMBER_OF_NEWS = 20;

    @Rule
    public DaggerMockRule<AppComponent> daggerRule =
            new DaggerMockRule<>(AppComponent.class, new AppModule((MyApplication) InstrumentationRegistry.getInstrumentation()
                    .getTargetContext()
                    .getApplicationContext())).set(
                    new DaggerMockRule.ComponentSetter<AppComponent>() {
                        @Override
                        public void setComponent(AppComponent component) {
                            MyApplication app =
                                    (MyApplication) InstrumentationRegistry.getInstrumentation()
                                            .getTargetContext()
                                            .getApplicationContext();
                            app.setComponent(component);
                        }
                    });

    @Rule
    public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Mock
    private GuardianNewsRepositoryImpl repository;

    @Captor
    private ArgumentCaptor<GuardianNewsDataSource.GetNewsCallback> getNewsCallback;

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}
*/