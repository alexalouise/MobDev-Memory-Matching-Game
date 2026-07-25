package com.example.tp6_astorga_janier_yu;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Looper;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridCards;
    private TextView tvMoves;
    private ImageButton[] cardViews = new ImageButton[12];

    private final int[] images = {
            R.drawable.camel, R.drawable.camel,
            R.drawable.coala, R.drawable.coala,
            R.drawable.fox, R.drawable.fox,
            R.drawable.monkey, R.drawable.monkey,
            R.drawable.wolf, R.drawable.wolf,
            R.drawable.lion, R.drawable.lion
    };

    private final int[] values = new int[12];
    private final List<Integer> shuffledImages = new ArrayList<>();

    private int firstIndex = -1;
    private int secondIndex = -1;
    private boolean isBusy = false;
    private int moves = 0;
    private int matchedPairs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridCards = findViewById(R.id.gridCards);
        tvMoves = findViewById(R.id.tvMoves);
        findViewById(R.id.btnRestart).setOnClickListener(v -> startGame());

        startGame();
    }

    private void startGame() {
        gridCards.removeAllViews();
        shuffledImages.clear();

        moves = 0;
        matchedPairs = 0;
        firstIndex = -1;
        secondIndex = -1;
        isBusy = false;
        updateMoves();

        for (int image : images) {
            shuffledImages.add(image);
        }

        Collections.shuffle(shuffledImages);

        for (int i = 0; i < 12; i++) {
            values[i] = shuffledImages.get(i);

            View cardView = getLayoutInflater().inflate(R.layout.card_item, gridCards, false);
            ImageButton btnCard = cardView.findViewById(R.id.btnCard);

            final int index = i;
            btnCard.setImageResource(R.drawable.code);
            btnCard.setRotationY(0f);
            btnCard.setOnClickListener(v -> onCardClick(index));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            btnCard.setLayoutParams(params);

            cardViews[i] = btnCard;
            gridCards.addView(cardView);
        }
    }

    private void onCardClick(int index) {
        if (isBusy) return;
        if (index == firstIndex) return;
        if (!cardViews[index].isEnabled()) return;

        flipToImage(index);

        if (firstIndex == -1) {
            firstIndex = index;
        } else {
            secondIndex = index;
            moves++;
            updateMoves();
            checkMatch();
        }
    }

    private void flipToImage(int index) {
        ImageButton card = cardViews[index];
        card.setCameraDistance(8000 * getResources().getDisplayMetrics().density);

        ObjectAnimator rotateOut = ObjectAnimator.ofFloat(card, "rotationY", 0f, 90f);
        rotateOut.setDuration(150);

        ObjectAnimator rotateIn = ObjectAnimator.ofFloat(card, "rotationY", -90f, 0f);
        rotateIn.setDuration(150);

        rotateOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                card.setImageResource(values[index]);
                rotateIn.start();
            }
        });

        rotateOut.start();
    }

    private void flipBack(int index) {
        ImageButton card = cardViews[index];
        card.setCameraDistance(8000 * getResources().getDisplayMetrics().density);

        ObjectAnimator rotateOut = ObjectAnimator.ofFloat(card, "rotationY", 0f, 90f);
        rotateOut.setDuration(150);

        ObjectAnimator rotateIn = ObjectAnimator.ofFloat(card, "rotationY", -90f, 0f);
        rotateIn.setDuration(150);

        rotateOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                card.setImageResource(R.drawable.code);
                rotateIn.start();
            }
        });

        rotateOut.start();
    }

    private void checkMatch() {
        isBusy = true;

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (values[firstIndex] == values[secondIndex]) {
                cardViews[firstIndex].setEnabled(false);
                cardViews[secondIndex].setEnabled(false);
                cardViews[firstIndex].setAlpha(0.4f);
                cardViews[secondIndex].setAlpha(0.4f);
                matchedPairs++;

                if (matchedPairs == 6) {
                    Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
                }
            } else {
                flipBack(firstIndex);
                flipBack(secondIndex);
            }

            firstIndex = -1;
            secondIndex = -1;
            isBusy = false;
        }, 700);
    }

    private void updateMoves() {
        tvMoves.setText("Moves: " + moves);
    }
}