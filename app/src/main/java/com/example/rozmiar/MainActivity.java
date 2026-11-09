package com.example.rozmiar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvSizeLabel;
    private TextView tvQuote;
    private SeekBar seekBar;
    private Button btnNext;

    private final String[] quotes = {"Dzień dobry", "Good morning", "Buenos dias"};
    private int currentQuoteIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int densityPadding = (int) (20 * v.getContext().getResources().getDisplayMetrics().density);
            v.setPadding(
                    systemBars.left + densityPadding,
                    systemBars.top + densityPadding,
                    systemBars.right + densityPadding,
                    systemBars.bottom + densityPadding
            );
            return insets;
        });

        tvSizeLabel = findViewById(R.id.tvSizeLabel);
        tvQuote = findViewById(R.id.tvQuote);
        seekBar = findViewById(R.id.seekBar);
        btnNext = findViewById(R.id.btnNext);

        int initialProgress = seekBar.getProgress();
        tvSizeLabel.setText("Rozmiar: " + initialProgress);
        tvQuote.setText(quotes[currentQuoteIndex]);
        if (initialProgress > 0) {
            tvQuote.setTextSize(initialProgress);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSizeLabel.setText("Rozmiar: " + progress);

                if (progress > 0) {
                    tvQuote.setTextSize(progress);
                } else {
                    tvQuote.setTextSize(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnNext.setOnClickListener(v -> {
            currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;

            tvQuote.setText(quotes[currentQuoteIndex]);
        });
    }
}
